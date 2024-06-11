<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en"
      xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Rasmiy</title>
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
            rel="stylesheet"
    />
    <script>function data() {
            function getThemeFromLocalStorage() {
                // if user already changed the theme, use it
                if (window.localStorage.getItem('dark')) {
                    return JSON.parse(window.localStorage.getItem('dark'))
                }

                // else return their preferences
                return (
                    !!window.matchMedia &&
                    window.matchMedia('(prefers-color-scheme: dark)').matches
                )
            }

            function setThemeToLocalStorage(value) {
                window.localStorage.setItem('dark', value)
            }

            return {
                dark: getThemeFromLocalStorage(),
                toggleTheme() {
                    this.dark = !this.dark
                    setThemeToLocalStorage(this.dark)
                },
                isSideMenuOpen: false,
                toggleSideMenu() {
                    this.isSideMenuOpen = !this.isSideMenuOpen
                },
                closeSideMenu() {
                    this.isSideMenuOpen = false
                },
                isNotificationsMenuOpen: false,
                toggleNotificationsMenu() {
                    this.isNotificationsMenuOpen = !this.isNotificationsMenuOpen
                },
                closeNotificationsMenu() {
                    this.isNotificationsMenuOpen = false
                },
                isProfileMenuOpen: false,
                toggleProfileMenu() {
                    this.isProfileMenuOpen = !this.isProfileMenuOpen
                },
                closeProfileMenu() {
                    this.isProfileMenuOpen = false
                },
                isPagesMenuOpen: false,
                togglePagesMenu() {
                    this.isPagesMenuOpen = !this.isPagesMenuOpen
                },
                // Modal
                isModalOpen: false,
                trapCleanup: null,
                openModal() {
                    this.isModalOpen = true
                    this.trapCleanup = focusTrap(document.querySelector('#modal'))
                },
                closeModal() {
                    this.isModalOpen = false
                    this.trapCleanup()
                },
            }
        }
    </script>

    <link rel="stylesheet" href="../../static/css/tailwind.output.css">
    <script
            src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
            defer
    ></script>

    <script src="../../static/js/init-alpine.js"></script>

    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css"
    />
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
            defer
    ></script>
    <script src="../../static/js/charts-lines.js" defer></script>
    <script src="../../static/js/charts-pie.js" defer></script>
</head>
<body>
<div
        class="flex h-screen bg-gray-50 dark:bg-gray-900"
        :class="{ 'overflow-hidden': isSideMenuOpen }"
>

    <aside
            class="z-20 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block flex-shrink-0"
    >
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a
                    class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
                    href="#"
            >
                Rasmiy
            </a>
            <ul class="mt-6">
                <li class="relative px-6 py-3">
              <span
                      class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
                      aria-hidden="true"
              ></span>
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
                            href="index.html"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                            ></path>
                        </svg>
                        <span class="ml-4"> <@spring.message 'documents'/></span>
                    </a>
                </li>
            </ul>
            <ul>

                <li class="relative px-6 py-3">
                    <button
                            class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            @click="togglePagesMenu"
                            aria-haspopup="true"
                    >
                <span class="inline-flex items-center">
                  <svg
                          class="w-5 h-5"
                          aria-hidden="true"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                  >
                    <path
                            d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z"
                    ></path>
                  </svg>
                  <a href="/logout"> <span class="ml-4"><@spring.message 'logout'/></span></a>
                </span>
                    </button>

                </li>
            </ul>
        </div>
    </aside>

    <div
            x-show="isSideMenuOpen"
            class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"
    ></div>
    <aside
            class="fixed inset-y-0 z-20 flex-shrink-0 w-64 mt-16 overflow-y-auto bg-white dark:bg-gray-800 md:hidden"
            x-show="isSideMenuOpen"

            @click.away="closeSideMenu"
            @keydown.escape="closeSideMenu"
    >
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a
                    class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
                    href="#"
            >
                Rasmiy
            </a>
            <ul class="mt-6">
                <li class="relative px-6 py-3">
              <span
                      class="absolute inset-y-0 left-0 w-1 bg-purple-600 rounded-tr-lg rounded-br-lg"
                      aria-hidden="true"
              ></span>
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100"
                            href="index.html"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                            ></path>
                        </svg>
                        <span class="ml-4"> <@spring.message 'documents'/></span>
                    </a>
                </li>
            </ul>
            <ul>
                <li class="relative px-6 py-3">
                    <button
                            class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            @click="togglePagesMenu"
                            aria-haspopup="true"
                    >
                <span class="inline-flex items-center">
                  <svg
                          class="w-5 h-5"
                          aria-hidden="true"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                  >
                    <path
                            d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z"
                    ></path>
                  </svg>
                  <a href="/logout"> <span class="ml-4"><@spring.message 'logout'/></span></a>
                </span>
                    </button>
                </li>
            </ul>

        </div>
    </aside>
    <div class="flex flex-col flex-1 w-full">
        <header class="z-10 py-4 bg-white shadow-md dark:bg-gray-800">
            <div
                    class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300"
            >
                <button
                        class="p-1 mr-5 -ml-1 rounded-md md:hidden focus:outline-none focus:shadow-outline-purple"
                        @click="toggleSideMenu"
                        aria-label="Menu"
                >
                    <svg
                            class="w-6 h-6"
                            aria-hidden="true"
                            fill="currentColor"
                            viewBox="0 0 20 20"
                    >
                        <path
                                fill-rule="evenodd"
                                d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
                                clip-rule="evenodd"
                        ></path>
                    </svg>
                </button>
                <div class="flex justify-center flex-1 lg:mr-32">
                    <div
                            class="relative w-full max-w-xl mr-6 focus-within:text-purple-500"
                    >
                        <div class="absolute inset-y-0 flex items-center pl-2">
                            <svg
                                    class="w-4 h-4"
                                    aria-hidden="true"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                            >
                                <path
                                        fill-rule="evenodd"
                                        d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                                        clip-rule="evenodd"
                                ></path>
                            </svg>
                        </div>
                        <input
                                class="w-full pl-8 pr-2 text-sm text-gray-700 placeholder-gray-600 bg-gray-100 border-0 rounded-md dark:placeholder-gray-500 dark:focus:shadow-outline-gray dark:focus:placeholder-gray-600 dark:bg-gray-700 dark:text-gray-200 focus:placeholder-gray-500 focus:bg-white focus:border-purple-300 focus:outline-none focus:shadow-outline-purple form-input"
                                type="text"
                                placeholder="<@spring.message 'search'/>"
                                aria-label="Search"
                        />
                    </div>
                </div>
                <ul class="flex items-center flex-shrink-0 space-x-6">

                    <li class="flex">
                        <button
                                class="rounded-md focus:outline-none focus:shadow-outline-purple"
                                @click="toggleTheme"
                                aria-label="Toggle color mode"
                        >
                            <template x-if="!dark">
                                <svg
                                        class="w-5 h-5"
                                        aria-hidden="true"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                >
                                    <path
                                            d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"
                                    ></path>
                                </svg>
                            </template>
                            <template x-if="dark">
                                <svg
                                        class="w-5 h-5"
                                        aria-hidden="true"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                >
                                    <path
                                            fill-rule="evenodd"
                                            d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z"
                                            clip-rule="evenodd"
                                    ></path>
                                </svg>
                            </template>
                        </button>
                    </li>


                    <!-- Notifications menu -->
                    <li class="relative">
                        <button
                                class="relative align-middle rounded-md focus:outline-none focus:shadow-outline-purple"
                                @click="toggleNotificationsMenu"
                                @keydown.escape="closeNotificationsMenu"
                                aria-label="Notifications"
                                aria-haspopup="true"
                        >
                            <svg
                                    class="w-5 h-5"
                                    aria-hidden="true"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                            >
                                <path
                                        d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z"
                                ></path>
                            </svg>
                            <!-- Notification badge -->
                            <span
                                    aria-hidden="true"
                                    class="absolute top-0 right-0 inline-block w-3 h-3 transform translate-x-1 -translate-y-1 bg-red-600 border-2 border-white rounded-full dark:border-gray-800"
                            ></span>
                        </button>
                        <template x-if="isNotificationsMenuOpen">

                        </template>
                    </li>

                    <li class="flex">
                        <div class="relative" x-data="{ isOpen: false, language: 'kg' }">
                            <button @click="isOpen = !isOpen"
                                    class="rounded-md flex items-center focus:outline-none focus:shadow-outline-purple"
                                    aria-label="Toggle Language Menu">
                                <!-- Language text -->
                                <span class="mx-2"><@spring.message 'lang'/></span>
                            </button>
                            <!-- Language dropdown menu -->
                            <ul x-show="isOpen" @click.away="isOpen = false"
                                class="absolute right-0 w-28 py-1 mt-2 bg-white border border-gray-200 rounded-md shadow-lg">
                                <li>
                                    <a @click="setLanguage('kg')" href="?lang=en"
                                       class="block px-4 py-2 text-sm text-gray-700 text-center"
                                       x-bind:class="{ 'bg-gray-100': language === 'kg' }">Кыргызча</a>
                                </li>
                                <li>
                                    <a @click="setLanguage('ru')" href="?lang=ru"
                                       class="block px-4 py-2 text-sm text-gray-700 text-center"
                                       x-bind:class="{ 'bg-gray-100': language === 'ru' }">Русский</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </header>
        <main class="h-full overflow-y-auto">
            <div class="container px-6 mx-auto grid">
                <h2
                        class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200"
                >
                    <@spring.message 'documents'/>
                </h2>
                <a
                        class="flex items-center justify-between p-4 mb-8 text-sm font-semibold text-purple-100 bg-purple-600 rounded-lg shadow-md focus:outline-none focus:shadow-outline-purple"
                >
                    <div class="flex items-center">
                        <svg
                                class="w-5 h-5 mr-2"
                                fill="currentColor"
                                viewBox="0 0 20 20"
                        >
                            <path
                                    d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"
                            ></path>
                        </svg>
                        <span> <@spring.message 'documents.header'/></span>
                    </div>

                </a>
                <!-- Cards -->
                <div class="grid gap-6 mb-8 md:grid-cols-2 xl:grid-cols-4">

                    <#list Documents as document >
                        <a href="/dashboard/docs/${document.id}">
                            <!-- Card -->
                            <div
                                    class="flex items-center p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800"
                            >
                                <div
                                        class="p-3 mr-4 text-orange-500 bg-orange-100 rounded-full dark:text-orange-100 dark:bg-orange-500"
                                >
                                    <#if document.code=="code001">
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://static.vecteezy.com/system/resources/previews/016/877/710/non_2x/authorization-manager-blue-icon-cloud-computing-symbol-eps-10-file-vector.jpg"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    <#elseif document.code=="code002">
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://www.shutterstock.com/image-vector/continuing-education-line-icon-online-600nw-1222296163.jpg"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    <#elseif document.code=="code003">
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://img.freepik.com/premium-vector/cartoon-house-icon-comic-style-home-illustration-pictogram-house-splash-business-concept_157943-3420.jpg"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    <#elseif document.code=="code004">
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://media.baamboozle.com/uploads/images/435410/1648130589_20675.jpeg"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    <#elseif document.code=="code005">
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://static.vecteezy.com/system/resources/previews/013/259/645/original/time-management-self-management-self-control-target-productivity-metaphors-modern-flat-cartoon-style-illustration-on-white-background-vector.jpg"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    <#elseif document.code=="code006">
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://www.kindpng.com/picc/m/387-3870484_transparent-delivery-van-clipart-png-delivery-van-cartoon.png"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    <#else>
                                        <img
                                                class="object-cover rounded-full"
                                                src="https://cdn-icons-png.flaticon.com/512/3301/3301750.png"
                                                alt=""
                                                loading="lazy"
                                                style="width: 60px; height: 60px;"
                                        />
                                    </#if>

                                </div>
                                <div>
                                    <p
                                            class="mb-2 text-sm font-medium text-gray-600 dark:text-gray-400"

                                    >
                                        <#if lang=="en">
                                            ${document.document_name_kyrgyz}
                                        <#else>
                                            ${document.document_name_russian}
                                        </#if>

                                    </p>
                                    <p
                                            class="text-lg font-semibold text-gray-700 dark:text-gray-200"
                                    >
                                        ${document.subDocumentCount}
                                    </p>
                                </div>
                            </div>
                        </a>
                    </#list>

                </div>

            </div>
        </main>
    </div>
</div>

</body>
</html>
